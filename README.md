![app-doctor](https://raw.githubusercontent.com/josketres/app-doctor/master/art/app-doctor-logo.png)

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


License
-------

    Copyright 2015 eSailors IT Solutions GmbH

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
