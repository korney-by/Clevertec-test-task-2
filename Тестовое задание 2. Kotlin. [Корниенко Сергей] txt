fun main() {
    print("Животных в Зоопарке: ${Zoo.quantityAnimals}")
    Zoo.showAllAnimals()
    println()
    println()
    Zoo.startLife()
}

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

enum class AnimalType(val value: String) {
    DUCK("Утка"),
    HUMMINGBIRD("Колибри"),
    BEAR("Медведь"),
    WOLF("Волк"),
    PEACOCK("Павлин"),
    BEAVER("Бобёр"),
}

enum class AnimalState(val value: String) {
    SLEEP("спит"),
    EAT("ест"),
    POOP("какает"),
    WALK("идет"),
    FLY("летит"),
    SWIM("плывет"),
    CLIMB_TREES("лезет на дерево"),
    LAY_EGGS("откладывает яйца"),
    FED_MILK("кормит молоком детенышей"),
    SHOW_TAIL("показывает хвост"),
    WAIT_RED_HAT("ждет Красную шапочку"),
    BUILD_DAM("строит плотину")
}

interface Walk {
    fun walk()
}

interface Fly {
    fun fly()
}

interface ShowTail {
    fun showTail()
}

interface Swim {
    fun swim()
}

interface ClimbTrees {
    fun climbTrees()
}

interface WaitRedHat {
    fun waitRedHat()
}

interface Poop {
    fun poop()
}

interface Eat {
    fun eat()
}

interface Sleep {
    fun sleep()
}

interface LayEggs {
    fun layEggs()
}

interface FedMilk {
    fun fedMilk()
}

interface BuildDam {
    fun buildDam()
}

abstract class Animal : Runnable, Eat, Poop, Sleep {
    open val animalType: AnimalType? = null
    open var animalName: String = ""
        protected set
    protected val actions = mutableListOf<() -> Unit>()
    private var onChangeState: ((animal: Animal) -> Unit)? = null

    var state: AnimalState = AnimalState.SLEEP
        protected set(value) {
            field = value
            onChangeState?.invoke(this)
        }

    init {
        actions.addAll(listOf(
            { sleep() }, { eat() }, { poop() }
        ))
    }

    fun setOnStateChangeListener(listener: (animal: Animal) -> Unit) {
        onChangeState = listener
    }

    protected fun generateAnimalName(id: Int): String {
        return animalType?.value + "-$id"
    }

    override fun run() {
        // 10 evens in the life of each animal
        // last event - animal goes to sleep
        val eventsCount = 10
        for (i in 0 until eventsCount) {
            if (i != eventsCount - 1) actions.random().invoke() else sleep()
            Thread.sleep((500..2000).random().toLong())
        }
    }

    override fun eat() {
        state = AnimalState.EAT
    }

    override fun sleep() {
        state = AnimalState.SLEEP
    }

    override fun poop() {
        state = AnimalState.POOP
    }
}

abstract class Bird : Animal(), LayEggs {
    init {
        actions.add { layEggs() }
    }

    override fun layEggs() {
        state = AnimalState.LAY_EGGS
    }
}

abstract class Mammals : Animal(), Walk, FedMilk {
    init {
        actions.addAll(listOf(
            { fedMilk() }, { walk() }
        ))
    }

    override fun fedMilk() {
        state = AnimalState.FED_MILK
    }

    override fun walk() {
        state = AnimalState.WALK
    }
}

class Duck (id: Int) : Bird(), Walk, Fly, Swim {
    override val animalType = AnimalType.DUCK

    init {
        animalName = generateAnimalName(id)
        actions.addAll(listOf(
            { walk() }, { fly() }, { swim() }
        ))
    }

    override fun walk() {
        state = AnimalState.WALK
    }

    override fun fly() {
        state = AnimalState.FLY
    }

    override fun swim() {
        state = AnimalState.SWIM
    }
}

class Peacock (id: Int) : Bird(), Walk, Fly, ShowTail {
    override val animalType = AnimalType.PEACOCK

    init {
        animalName = generateAnimalName(id)
        actions.addAll(listOf(
            { fly() }, { showTail() }
        ))
    }

    override fun walk() {
        state = AnimalState.WALK
    }

    override fun fly() {
        state = AnimalState.FLY
    }

    override fun showTail() {
        state = AnimalState.SHOW_TAIL
    }
}

class Hummingbird (id: Int) : Bird(), Fly {
    override val animalType = AnimalType.HUMMINGBIRD

    init {
        animalName = generateAnimalName(id)
        actions.add { fly() }
    }

    override fun fly() {
        state = AnimalState.FLY
    }

}

class Bear (id: Int) : Mammals(), ClimbTrees {
    override val animalType = AnimalType.BEAR

    init {
        animalName = generateAnimalName(id)
        actions.add { climbTrees() }
    }

    override fun climbTrees() {
        state = AnimalState.CLIMB_TREES
    }
}

class Wolf (id: Int) : Mammals(), WaitRedHat {
    override val animalType = AnimalType.WOLF

    init {
        animalName = generateAnimalName(id)
        actions.add { waitRedHat() }
    }

    override fun waitRedHat() {
        state = AnimalState.WAIT_RED_HAT
    }
}

class Beaver (id: Int) : Mammals(), Swim, BuildDam {
    override val animalType = AnimalType.BEAVER

    init {
        animalName = generateAnimalName(id)
        actions.addAll(listOf(
            { swim() }, { buildDam() }
        ))
    }

    override fun swim() {
        state = AnimalState.SWIM
    }

    override fun buildDam() {
        state = AnimalState.BUILD_DAM
    }
}