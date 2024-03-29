package cn.yumetsuki.lab4

abstract class AbstractFile(override val path: String): FileInterface {


    override val name: String
        get() = path.split("/").run {
            get(size - 1)
        }

    val location: String = "233"

    val size: Double = 2.0

    var permission: Permission = Permission.R

}

enum class Permission(val code: String){
    R("001"), W("010"), WR("011"), RE("101"), WE("110"), WRE("111"),
    E("100")
}