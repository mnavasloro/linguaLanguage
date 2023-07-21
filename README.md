# linguaLanguage
Auxiliary Java project to add a new Language to [Lingua](https://github.com/pemistahl/lingua).

Follow instructions in [Lingua](https://github.com/pemistahl/lingua)'s README (section 10.1) until step 5. We copy them below:

1. Clone *Lingua's* repository to your own computer as described in [section 8][library build url].
2. Open enums [`IsoCode639_1`][isocode639_1 url] and [`IsoCode639_3`][isocode639_3 url] and add the
   language's iso codes. Among other sites, Wikipedia provides a [comprehensive list][wikipedia isocodes list].
3. Open enum [`Language`][language url] and add a new entry for your language. If the language is written
   with a script that is not yet supported by *Lingua's* [`Alphabet`][alphabet url] enum, then add a new entry
   for it there as well.
4. If your language's script contains characters that are completely unique to it, then add them to the
   respective entry in the [`Language`][language url] enum. However, if the characters occur in more than one
   language **but** not in all languages, then add them to the
   [`CHARS_TO_LANGUAGES_MAPPING`][chars to languages mapping url] constant in class `Constant` instead.


## FROM HERE WE INCLUDE ADDITIONAL INSTRUCTIONS

From the root folder lingua, compile using:
`./gradlew jarWithDependencies --warning-mode all`


5. Use [`LanguageModelFilesWriter`][language model files writer url] to create the language model files.
   The training data file used for ngram probability estimation is not required to have a specific format
   other than to be a valid txt file. Please don’t include very long paragraphs, it may throw an error (when longer than 4096) in step 9. For this step:
   
   5.1. Clone this repo and copy there the lingua-with-dependencies.jar file from lingua\build\libs

   5.2. Prepare there an input folder with two txt files as in the case of Gallego.

   5.3. Prepare two empty output folders as in the case of “gl” and “gl-test”, and modify “AppData.kt” and “AppTest.kt” to pass the correct folders.

   5.4. Execute the following in the command line (install kotlinc, a kotlin compiler, if it is not installed): 

   `kotlinc -classpath lingua-with-dependencies.jar AppData.kt -d AppData.jar`

   Now execute:

   `java -cp "lingua-with-dependencies.jar;App.jar" com.github.pemistahl.lingua.app.AppKt`

   The output should be:

   `Start.
   Test.
   This worked.`

7. Create a new subdirectory in [`/src/main/resources/language-models`][language models directory url]
   and put the generated language model files in there. Do **not** rename the language model files.
   The name of the subdirectory **must** be the language's ISO 639-1 code, completely lowercased.
8. Use [`TestDataFilesWriter`][test data files writer url] to create the test data files used for
   accuracy report generation. The input file from which to create the test data should have each
   sentence on a separate line. To do so, execute: 

   `kotlinc -classpath lingua-with-dependencies.jar AppData.kt -d AppData.jar`

   Now execute:

   `java -cp "lingua-with-dependencies.jar;App.jar" com.github.pemistahl.lingua.app.AppKt`



## FROM HERE WE GO BACK TO ORIGINAL INSTRUCTIONS (but I would say you can skip them if not interested in the Accuracy)

8. Put the generated test data files in [`/src/accuracyReport/resources/language-testdata`][test data directory url].
   Do **not** rename the test data files.
9. For accuracy report generation, create an abstract base class for the main logic in
   [`/src/accuracyReport/kotlin/com/github/pemistahl/lingua/report/config`][accuracy report config url].
   Look at the other languages' files in this directory to see how the class must look like.
   It should be pretty self-explanatory.
10. Create a concrete test class in
    [`/src/accuracyReport/kotlin/com/github/pemistahl/lingua/report/lingua`][accuracy report lingua url].
    Look at the other languages' files in this directory to see how the class must look like.
    It should be pretty self-explanatory. If one of the other language detector libraries
    supports your language already, you can add test classes for those as well. Each library
    has its own directory for this purpose. If your language is not supported by the other
    language detector libraries, exclude it in [`AbstractLanguageDetectionAccuracyReport`][accuracy report nonlingua url].
11. Fix the existing unit tests by adding your new language.
12. Add your new language to property [`linguaSupportedLanguages`][gradle properties url]
    in `/gradle.properties`.
13. Run `./gradlew accuracyReport` and add the updated accuracy reports to your pull request.
14. Run `./gradlew drawAccuracyPlots` and add the updated plots to your pull request.
15. Run `./gradlew writeAccuracyTable` and add the updated accuracy table to your pull request.
16. Be happy! :-) You have successfully contributed a new language and have thereby significantly widened
    this library's fields of application.



Now you can compile and get a functioning jar with the new language(s):
`./gradlew jarWithDependencies --warning-mode all`
