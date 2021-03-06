namespace java com.xteam.crycat.thrift.generated

struct Response {
    1: required bool success;

    2: optional string data;

    3: required string msg;

    4: required string code;
}


struct Request {
    //调用方法
    1: required string method;

    //方法参数
    2: required string params;
}

service RemoteService {

    Response invoke(1: required Request request)

}

