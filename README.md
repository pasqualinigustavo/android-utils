Welcome to AndroidUtils!
===================


I created these features to make easy and improve the performance on new Android Projects.
Fell free to use and share!!

----------


Features
-------------

These are the features presented in AndroidUtils.

> **Note:**

> - If you want to contribute, please send me email: pasqualini.gustavo@gmail.com

#### SharedPreferencesUtils

Singleton to create, read and write on SharedPreferences.
##### How to use:

<code> //instantiate
SharedPreferencesUtils.init(getApplicationContext());
//or
SharedPreferencesUtils.init(getApplicationContext(),"NAME_OF_SHARED_PREFERENCES");
//write a value (string example)
SharedPreferencesUtils.getInstance().save("TAG", value);
//read value (string example)
SharedPreferencesUtils.getInstance().getValue("TAG",String.class)

#### StringUtils

Utils for strings.
##### How to use:
<code> if(StringUtils.isNullOrEmpty("string")) { }

#### SnackBarUtils

Utils for SnackBar(Android Materials).
##### How to use:
<code>SnackBarUtils.presentSnackFromView(view_container, "No internet connection");

#### LoadingUtils

Utils for show/hide loading view.
##### How to use:
<code>//call
LoadingUtils.show(context);
//in xml 
include layout="@layout/view_loading"
</code>

----------

Adding Dependency - Gradle
-------------------

<code> compile 'com.'

----------

License
--------------------

> Copyright 2016 Gustavo Pasqualini
> 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at <p>
http://www.apache.org/licenses/LICENSE-2.0 <p>
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
<p>
See the License for the specific language governing permissions and
limitations under the License.
