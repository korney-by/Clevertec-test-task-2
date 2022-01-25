package animals

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
