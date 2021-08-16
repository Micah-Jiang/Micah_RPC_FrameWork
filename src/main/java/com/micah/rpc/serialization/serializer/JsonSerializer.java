package com.micah.rpc.serialization.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micah.rpc.common.msg.RpcRequest;
import com.micah.rpc.common.msg.RpcResponse;

/**
 * @Author m.kong
 * @Date 2021/8/16 下午2:07
 * @Version 1
 * @Description json序列化器
 * 由于json序列化的方式是通过把对象转化成字符串，丢失了Data对象的类信息，所以deserialize需要
 * 了解对象的类信息，根据类信息把JsonObject -> 对应的对象
 */
public class JsonSerializer implements Serializer{
    @Override
    public byte[] serializable(Object obj) {
        return JSONObject.toJSONBytes(obj);
    }

    @Override
    public Object deserializable(byte[] bytes, int messageType) {
        Object obj = null;
        //传输的消息类型分为Request和Response
        switch (messageType){
            case 0:
                RpcRequest request = JSON.parseObject(bytes, RpcRequest.class);

                if(request.getParams() == null) {
                    return request;
                }

                Object[] objects = new Object[request.getParams().length];
                // 把json字串转化成对应的对象， fastjson可以读出基本数据类型，不用转化
                for(int i = 0; i < objects.length; i++){
                    Class<?> paramsType = request.getParamsTypes()[i];
                    if (!paramsType.isAssignableFrom(request.getParams()[i].getClass())){
                        objects[i] = JSONObject.toJavaObject((JSONObject) request.getParams()[i],request.getParamsTypes()[i]);
                    }else{
                        objects[i] = request.getParams()[i];
                    }

                }
                request.setParams(objects);
                obj = request;
                break;
            case 1:
                //序列化字节数组反序列化RpcResponse对象
                RpcResponse response = JSONObject.parseObject(bytes, RpcResponse.class);
                Class<?> dataType = response.getDataType();
                if(! dataType.isAssignableFrom(response.getData().getClass())){
                    response.setData(JSONObject.toJavaObject((JSONObject) response.getData(),dataType));
                }
                obj = response;
                break;
            default:
                System.out.println("不支持此种类型！");
                break;
        }
        return obj;
    }

    @Override
    public int getType() {
        //代表json序列化方式
        return 1;
    }
}
