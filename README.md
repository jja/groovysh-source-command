A new groovysh command "source".

":load" does most of this already.
This was started before I found load, and continued out of curiosity.
Maybe one day I'll propose changes to :load.

Adds "-v" arg to increase verbosity without
having to run the whole groovysh in verbose mode.

Finds files as "foo" or "foo.groovy"

Does not load URLs.

## Installation

    ./gradlew build
    cp build/libs/*jar ~/.groovy/libs
    echo ":register edu.ucar.groovy.tools.shell.SourceCommand" >> ~/.groovy/groovysh.profile

