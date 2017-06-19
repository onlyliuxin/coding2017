package cn.net.pikachu.base

class PromotionMailContentTemplate(val params: MutableMap<String, Any>): MailContentTemplate(){
    /**
     * 渲染成字符串
     * @param params
     * *
     * @return
     */
    override fun render(): String {
        return """
尊敬的 ${params["username"]} 您关注的产品 ${params["productName"]} 降价了，欢迎购买!
"""
    }
}
