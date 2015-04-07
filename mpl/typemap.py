VOID = ":void"
INT = ":int"
INT_ARR = ":array<:int>"
INT_LIST = ":list<:int>"

P_JAVA_T = "javaType"
P_SER = "serializer"
P_SEP = "seperator"

type_map = {
    VOID: {
        P_JAVA_T: "void"
    },
    INT: {
        P_JAVA_T: "int",
        P_SER: "serializeInt",
        P_SEP: "// |:int"
    },
    INT_ARR: {
        P_JAVA_T: "int[]",
        P_SER: "serializeIntArray",
        P_SEP: "// |:intArray"
    },
    INT_LIST: {
        P_JAVA_T: "List<Integer>",
        P_SER: "serializeIntList",
        P_SEP: "// |:intList"
    }
}
