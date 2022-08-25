### How to run `ktlintCheck` properly
```shell
./gradlew clean ktlintCheck
```
Without `clean`, repeated `ktlintCheck` may incorrectly show "BUILD SUCCESSFUL".
