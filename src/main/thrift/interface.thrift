namespace java com.xteam.crycat.thrift.generated

struct Response {
    1: optional bool isSuccess;

    2: optional string data;

    3: optional string msg;
}


struct ExchangeParams {
    //交易所ID，唯一标识，必传，策略模块根据id识别交易所的服务类
    1: required string id;

    //交易所的配置参数：
    //必填参数包括：apiKey
    //其他参数根据交易所需要按需传入
    2: required map<string, string> params;
}

struct StrategyParams {
    //策略ID，策略唯一标识，保留字段，暂无实质用途
    1: required string id;

    //策略参数，根据策略不同传入参数不同，策略代码执行前会校验参数完整性
    2: required map<string, string> params;
}

service StrategyService {
    //一台服务器最多初始化5个策略实例
    //初始化一个策略实例,初始化后会返回策略实例ID
    Response init(1: required list<ExchangeParams> exchanges, 2: required StrategyParams strategy)

    //启动策略
    Response start(1: required string instance)

    //策略退出，完全退出，所有策略参数将被清空，必须重新执行初始化和启动
    Response exit(1: required string instance);

    //暂停策略
    Response pause(1: required string instance);

    //恢复策略，执行暂停后恢复，执行退出不可恢复
    Response restore(1: required string instance);

    //对策略实例运行时状态进行快照
    Response snapshot(1: required string instance);
}

