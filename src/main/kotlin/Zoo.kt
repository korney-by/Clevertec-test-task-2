import animals.*

object Zoo {
    private val animals = mutableListOf<Animal>()
    val quantityAnimals get() = animals.size

    init {
        animals.addAll(addAnimals(4, AnimalType.DUCK))
        animals.addAll(addAnimals(7, AnimalType.HUMMINGBIRD))
        animals.addAll(addAnimals(1, AnimalType.PEACOCK))
        animals.addAll(addAnimals(3, AnimalType.BEAR))
        animals.addAll(addAnimals(5, AnimalType.WOLF))
        animals.addAll(addAnimals(2, AnimalType.BEAVER))
    }

    fun startLife() {
        animals.forEach {
            Thread(it).start()
        }
    }

    private fun addAnimals(quantity: Int, animalType: AnimalType): List<Animal> {
        val animals = mutableListOf<Animal>()
        for (id in 1..quantity) {
            val newAnimal = when (animalType) {
                AnimalType.DUCK -> Duck(id)
                AnimalType.HUMMINGBIRD -> Hummingbird(id)
                AnimalType.PEACOCK -> Peacock(id)
                AnimalType.BEAR -> Bear(id)
                AnimalType.WOLF -> Wolf(id)
                AnimalType.BEAVER -> Beaver(id)
            }
            newAnimal.setOnStateChangeListener { animal ->
                showAnimalState(animal)
            }
            animals.add(newAnimal)
        }
        return animals.toList()
    }


    private fun showAnimalState(animal: Animal) {
        println("${animal.animalName} ${animal.state.value}")
    }

    fun showAllAnimals() {
        var previousAnimal = ""
        animals.forEach { animal ->
            if (previousAnimal != animal.animalType?.value) {
                println()
            }
            print("| ${animal.animalName} ${animal.state.value} ")
            animal.animalType?.let {
                previousAnimal = it.value
            }
        }
    }
}
