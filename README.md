# ESB Automation-automation
Automation Testing (API) Codebase for ESB Automation for the service KrEEMSEventDefinitionSubRMSII

add your respective environment

 -Dkarate.env=dev
 -Dkarate.env=test

mvn test "-Dkarate.options=--tags ~@ignore classpath:src/test/java/com/kroger/ec/platform/groups/GroupsGet.feature" -Dtest=RegressionRunner
