

# Introduction #
This extension adds logging information unobtrusively to the Concordion output. The logging information is only displayed when hovering over the tooltip.

This allows us to reveal implementation details in the Concordion output, without obscuring the intent of the specification.  For example:

> ![http://4.bp.blogspot.com/_EpHkfjCuniM/TI3lpp_SN_I/AAAAAAAAAZQ/Om1sxPvaf4M/s400/concordion-annotate.jpg](http://4.bp.blogspot.com/_EpHkfjCuniM/TI3lpp_SN_I/AAAAAAAAAZQ/Om1sxPvaf4M/s400/concordion-annotate.jpg)

Revealing the implementation detail can help to improve the level of trust in the tests, and sometimes highlights redundant steps in the tests. This is discussed further in  [this blog entry](http://tutansblog.blogspot.com/2010/09/whats-happening-in-my-acceptance-tests.html).

The extension captures the logging information from java.util.logging.

# Configuration #
## Default Configuration ##

By default, this extension will capture all output from the root logger and disable console logging of the root logger.

To install the extension:

```
System.setProperty("concordion.extensions", "org.concordion.ext.LoggingTooltipExtension");
```

## Custom Configuration ##
The extension can be customised to restrict the log output to named loggers, and by logging level. The output of logging to the console can also be enabled.

For example:

```
package carbon;

import java.util.logging.Level;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.ConcordionExtensionFactory;
import org.concordion.ext.LoggingTooltipExtension;

public class LoggingTooltipExtensionFactory implements ConcordionExtensionFactory {
    @Override
    public ConcordionExtension createExtension() {
        String loggers = "carbon.CarbonCalculatorTest, selenium.events";
        return new LoggingTooltipExtension(loggers, Level.FINE, false);
    }
}
```

To install this example extension factory:

```
System.setProperty("concordion.extensions", "carbon.LoggingTooltipExtensionFactory");
```

Thanks to Trent Richardson for the [CSS Tooltip](http://trentrichardson.com/examples/csstooltips/) implementation.