# app-doctor
Diagnose application's common startup problems by analysing log files

[![Build Status](https://travis-ci.org/eSailors/app-doctor.svg?branch=master)](https://travis-ci.org/eSailors/app-doctor)

## Usage

Create a database of common failure causes for your application.
```yaml
---
name: ClassNotFoundException-SovereignStateLoaderListener
pattern: .*ClassNotFoundException.*SovereignStateLoaderListener
description:
  Strange failure.
  It happens sometimes due to maven concurrent runs.
solution:
  Go to the frontend module and execute mvn clean install
  Then restart the webshop
```
