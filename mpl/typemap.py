t_void = ":void"
t_int = ":int"
t_int_arr = ":array<:int>"
t_int_list = ":list<:int>"

k_java_t = "javaType"
k_ser = "serializer"
k_sep = "seperator"

type_map = {
    t_void: {
        k_java_t: "void"
    },
    t_int: {
        k_java_t: "int",
        k_ser: "serializeInt",
        k_sep: "// |:int"
    },
    t_int_arr: {
        k_java_t: "int[]",
        k_ser: "serializeIntArray",
        k_sep: "// |:intArray"
    },
    t_int_list: {
        k_java_t: "List<Integer>",
        k_ser: "serializeIntList",
        k_sep: "// |:intList"
    }
}
