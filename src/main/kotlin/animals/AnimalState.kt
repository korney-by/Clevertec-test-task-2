package animals

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
