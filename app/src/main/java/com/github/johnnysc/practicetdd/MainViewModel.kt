package com.github.johnnysc.practicetdd

class MainViewModel(
    private val filters: List<GoodFilter>,
    private val products: List<Good>,
    private val communication: Communication<List<Good>>,
    private val filtersCommunication: Communication<List<GoodFilter>>
) {

    private val activeFilters = mutableListOf<GoodFilter>()

    init {
        communication.map(products)
        filtersCommunication.map(filters)
    }

    fun change(filter: GoodFilter) {
        if (!activeFilters.remove(filter)) {
            activeFilters.add(filter)
        }
        filtersCommunication.map(activeFilters)
        communication.map(products.filter { good ->
            activeFilters.all { filter -> good.map(filter) }
        })
    }
}