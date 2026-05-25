package com.github.johnnysc.practicetdd

class ViewModelChain(
    private val featureChain: FeatureChain
) : FeatureChain {

    private var nextFeatureChain: FeatureChain? = null

    override suspend fun handle(message: String): MessageUI {
        return if (featureChain is FeatureChain.CheckAndHandle && !featureChain.canHandle(message)) {
            nextFeatureChain?.handle(message) ?: MessageUI.Empty
        } else {
            featureChain.handle(message)
        }
    }

    fun nextFeatureChain(nextFeatureChain: FeatureChain) {
        this.nextFeatureChain = nextFeatureChain
    }
}