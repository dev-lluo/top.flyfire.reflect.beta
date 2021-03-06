package top.flyfire.reflect.metainfo;

import top.flyfire.reflect.$Type;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shyy_work on 2016/4/21.
 */
public class ClassMetaInfo extends $Type {
    private final Class<?> rawType;

    public final Class<?> getRawType() {
        return rawType;
    }

    private final Map<String,FieldMetaInfo> fieldMetaInfoMap;

    public Map<String,FieldMetaInfo> getFieldMetaInfoMap(){
        return this.fieldMetaInfoMap;
    }

    public void setFieldMetaInfo(String fieldName,FieldMetaInfo fieldMetaInfo){
        this.fieldMetaInfoMap.put(fieldName,fieldMetaInfo);
    }

    public ClassMetaInfo(Class<?> rawType) {
        super();
        this.rawType = rawType;
        this.fieldMetaInfoMap = new HashMap<>();
    }

    @Override
    protected String buildTypeName() {
        return this.rawType.getTypeName();
    }

}
