package com.xteam.crycat;

import com.alibaba.fastjson.JSONObject;
import com.xteam.crycat.base.StrategyEnums;
import com.xteam.crycat.thrift.Response;
import com.xteam.crycat.thrift.ThriftServer;
import com.xteam.crycat.utils.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class CrycatStrategyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrycatStrategyApplication.class, args);
		try {
			scanExistStrategy();    //启动前扫描存在在策略，防止意外关机或者停机后恢复
			ThriftServer.startServer();    //启动Thrift服务器
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description 扫描classpath下/conf目录
	 * 		1、扫描init_{id}文件，如果存在，执行create方法初始化
	 * 		2、扫描running_{id}文件，如果存在，resumeFromSnap从快照恢复到执行状态
	 * @author alyenc
	 * @date 2018/4/17 下午3:07
	 * @param
	 * @return
	 */
	private static void scanExistStrategy(){
		Boolean isExist = FileUtils.existsDirectory("conf");    //判断配置目录是否存在
		if(isExist) {
			List<File> files = FileUtils.listAll("conf");

			//获取初始化参数文件列表
			List<String> statusList = files.stream()
					.map(File::getName)
					.filter(item -> item.contains("status_"))
					.collect(Collectors.toList());

			//运行时数据列表
			List<String> runningList = files.stream()
					.map(File::getName)
					.filter(item -> item.contains("running_"))
					.collect(Collectors.toList());

			Map<String, Integer> statusResult = new HashMap<>();
			statusList.forEach(item -> {
				String initParams = FileUtils.readText("conf" + File.separator + item);
				JSONObject json = JSONObject.parseObject(initParams);
				String id = json.getString("id");
				Integer status = json.getInteger("status");

				statusResult.put(id, status);
			});

			statusResult.keySet().forEach(item -> {
				Integer status = statusResult.get(item);

				Boolean isCreated = false;
				if(status >= 0){
					String params = FileUtils.readText("conf" + File.separator + "init_" + item);
					Response resp = StrategyEnums.INSTANCE.getInstance().create(params);
					isCreated = resp.isSuccess();
				}

				Boolean isStarted = false;
				if(isCreated && status >= 1){
					JSONObject startJSON = new JSONObject();
					startJSON.put("id", item);
					Response resp = StrategyEnums.INSTANCE.getInstance().start(startJSON.toJSONString());
					isStarted = resp.isSuccess();
				}

				Boolean isSuspended = false;
				if(isStarted && status == 2){
					JSONObject startJSON = new JSONObject();
					startJSON.put("id", item);
					Response resp = StrategyEnums.INSTANCE.getInstance().suspend(startJSON.toJSONString());
					isSuspended = resp.isSuccess();
				}
			});
		}
	}
}
