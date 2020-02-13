// see groovysh "load" which also works with a URL

package edu.ucar.groovy.tools.shell

import org.codehaus.groovy.tools.shell.CommandSupport
import org.codehaus.groovy.tools.shell.Groovysh
import org.codehaus.groovy.tools.shell.Shell

class Source extends CommandSupport {

    protected Source(final Groovysh shell) {
        super(shell, 'source', ':ss')
    }

    public Object execute(List args) {
        def verbose = false
        if ('-v' == args[0]) {
            verbose = true
            args.remove(0)
            }

        // XXX args.each
        def fn = args.remove(0)

        // XXX file exceptions
        new File(fn).eachLine {
            if (verbose) println "$fn> $it"
            //shell.execute it
            shell << it
            }
    }

}
