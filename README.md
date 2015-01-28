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
---
name: Some other common failure
pattern: .*PropertyNotFoundException.*
description: This happens when the server cannot find the property-management service
solution: Start the property-management service and restart the webshop.
```

Get the latest `app-doctor-all-*.jar` and run app-doctor specifying the failure-database (yaml) and the location of your application's main log.
```sh
#!/bin/bash
log=/home/john/my-application/logs/my-app.catalina.out
java -jar libs/app-doctor-all-1.0.jar --db failure-causes.yaml --log $log
```

App-doctor will try to match your log file against the failure causes provided in the database yaml file and it will notify you if it detects any common failure cause.

```
[app-doctor] Scanning log file (/home/john/my-application/logs/my-app.catalina.out) 
[app-doctor] A failure cause was identified
[app-doctor] Name: ClassNotFoundException-SovereignStateLoaderListener
[app-doctor] Description: Strange failure. It happens sometimes due to maven concurrent runs.
[app-doctor] Solution: Go to the frontend module and execute mvn clean install Then restart the webshop.
```

Download
-------
Download [the latest JAR][1]

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

 [1]: https://search.maven.org/remote_content?g=com.josketres&a=builderator&v=LATEST
