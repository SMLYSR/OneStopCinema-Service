package org.joker.oscp.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.joker.oscp.common.CommonResult;

/**
 * REST信息对象转换
 * @author JOKER
 */
@NoArgsConstructor
@AllArgsConstructor
public class ResultDataConvertValue<T> {

    private ObjectMapper objectMapper = new ObjectMapper();
    private Object object;

    public <T> T obResultDataConvert(CommonResult commonResult,TypeReference<?> typeReference){
        object = commonResult.getData();
        T rs = objectMapper.convertValue(object, typeReference);
        return (T) rs;
    }

}
