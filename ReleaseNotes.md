**This library is now deprecated. The individual extensions have been released as separate projects. See the Concordion [Extensions](http://www.concordion.org/Extensions.html) page for details.**

# v1.1.0 #
  * Support method chaining in extensions to provide fluent way of configuring them [issue 15](http://code.google.com/p/concordion-extensions/issues/detail?id=15)
  * Fixed - Tooltip renderer fails on verify rows [issue 13](http://code.google.com/p/concordion-extensions/issues/detail?id=13)
  * Updated examples to use new @Extension and @Extensions annotations
  * Updated examples to Selenium 2.0 [issue 14](http://code.google.com/p/concordion-extensions/issues/detail?id=14)



# v1.0.2 #
  * New Embed extension for embedding HTML in output
  * New Translator extension for translating error message text
  * Fixed [issue 9](http://code.google.com/p/concordion-extensions/issues/detail?id=9) - Screenshot files are overwritten when executing test suite
  * Added specifications for all extensions

Thanks to kostya.marchenko for reporting [issue 9](https://code.google.com/p/concordion-extensions/issues/detail?id=9) and supplying a test case.

# v1.0.1 #

  * Moved packages to org.concordion.ext
  * Fixed [issue 3](http://code.google.com/p/concordion-extensions/issues/detail?id=3) - 	ScreenshotExtension stops View Stack Trace from working
  * Added project to Maven Central
  * Added Maven build to example code, in addition to Gradle build
  * Moved from Github to Google Code