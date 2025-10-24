rootProject.name = "truck-monitoring"

// Core
include("core:domain")
include("core:application")

// In/Primary/Drive adapters
include("api:http")
include("api:kafka")

// Out/Secondary/Driven adapters
include("infrastructure:orm")
include("infrastructure:persist:postgres")

// Configuration of whole application
include("configuration")

// Architecture tests of dependencies between modules of whole application
include("architecture-tests")