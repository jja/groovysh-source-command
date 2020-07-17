// see groovysh "load" which also works with a URL

package edu.ucar.groovy.tools.shell

// TODO: Groovy 3.0+ : org.apache.groovy.groovysh.CommandSupport
import org.codehaus.groovy.tools.shell.CommandSupport
import org.codehaus.groovy.tools.shell.Groovysh
import org.codehaus.groovy.tools.shell.Shell

class SourceCommand extends CommandSupport {

    protected SourceCommand(final Groovysh shell) {
        super(shell, 'source', ':ss')
    }

    String getUsage() {
        '[-v] file1 file2.groovy ...'
    }

    String getDescription() {
        'Interpret commands from files'
    }

    String getHelp() {
        '''\
Like :load but only for local filenames, which can be specified
with or without trailing ".groovy".

Optional "-v" argument allows verbosity without making the
whole shell be verbose.

Examples:

    source foo      // finds "foo" or "foo.groovy"
    :ss -v bar.groovy
        '''
    }

    public Object execute(List<String> args) {
        def verbose = false
        if ('-v' == args[0]) {
            verbose = true
            args.remove(0)
            }

        String rval
        args.each { filename ->
            def f = new File(filename)
            if (!f.exists() && !filename.endsWith('.groovy')) {
                f = new File(filename+'.groovy')
                if (!f.exists()) {
                    fail "File not found: $filename or $f"
                    return
                }
            }

            try { f.eachLine {
                if (verbose) io.out.println "$filename> $it"
                rval = ( shell << it ) as String
            } } catch (e) {
                log.error "$e"
            }
        }

    rval
    }

}
