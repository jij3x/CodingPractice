VOID = ":void"
INT = ":int"
INT_ARR = ":array<:int>"
INT_VEC = ":vector<:int>"

P_JAVA_T = "javaType"
P_SER = "serializer"
P_DES = "deserializer"

type_map = {
    VOID: {
        P_JAVA_T: "void"
    },
    INT: {
        P_JAVA_T: "int",
        P_SER: "serializeInt",
        P_DES: "deserializeInt"
    },
    INT_ARR: {
        P_JAVA_T: "int[]",
        P_SER: "serializeIntArray",
        P_DES: "deserializeIntArray"
    },
    INT_VEC: {
        P_JAVA_T: "ArrayList<Integer>",
        P_SER: "serializeIntVector",
        P_DES: "deserializeIntVector"
    }
}