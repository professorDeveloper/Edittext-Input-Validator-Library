# Edittext-Input-Validator-Library

## Overview
Provides a custom component of Edittext, that facility create forms, and its validations, as require a little lines of code for use

## Changelog


## Requirements

* Project migrated or implemented *AndroidX components*

## Install

Gradle dependency:
```Groovy
 implementation 'com.github.professorDeveloper:Edittext-Input-Validator-Library:1.0.0'
```

Maven dependency:
```XML
	<dependency>
	    <groupId>com.github.professorDeveloper</groupId>
	    <artifactId>Edittext-Input-Validator-Library</artifactId>
	    <version>1.0.0</version>
	</dependency>
```
## Proguard
it isn't necesary exclude something 


## Usage

The next section explains how to use this, if you are need to see running this feel free of download the repo and run the sample

##### In XML:

You can use Edittext alone or inside in TextInputLayout

In this example you can see a Edittext of email field, with autovalidate enabled and automatic show errors in realtime

```XML
  
        <com.azamovhudstc.validator_lib.ValidatorEditText
            android:id="@+id/first_et"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:format="id" />
```

if you want to validate a specific pattern can do something like this:

```XML
<com.azamovhudstc.validator_lib.ValidatorSpinner
            android:id="@+id/validator_spinner"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="20dp"
            app:hint="select country" />
```


The next table contains all information about of custom attributes with their description


| name  | type | description |
|---|---|---|
|  app:format | reference  | This feature configure the types that are supported. The types are: **email, password, phone, zipcode, text, number, cellphone, date, personName, numberCurrency, curp, numberCurrencyRounded** |
