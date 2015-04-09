VOID = ":void"
INT = ":int"
TEXT = ":text"
INT_ARR = ":array<:int>"
INT_VEC = ":vector<:int>"
INT_SLLIST = ":sllist<:int>"
INT_UDGRAPH = ":udgraph<:int>"
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
    TEXT: {
        P_JAVA_T: "String",
        P_SER: "serializeText",
        P_DES: "deserializeText"
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
    },
    INT_SLLIST: {
        P_JAVA_T: "ListNode",
        P_SER: "serializeIntSLList",
        P_DES: "deserializeIntSLList"
    },
    INT_UDGRAPH: {
        P_JAVA_T: "UndirectedGraphNode",
        P_SER: "serializeIntUDGraph",
        P_DES: "deserializeIntUDGraph"
    }
}
