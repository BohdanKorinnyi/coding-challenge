# Coding Challenge

1) Create an endpoint which accepts a String parameter. The String parameter represents a JSON array. Convert all the values of each object in the JSON array to a MD5 hash of that value. Return the transformed JSON array in the response.

2) Create an endpoint which accepts two String parameters. Each String represents a path to an image stored in an S3 bucket. Analyze whether they're identical images. Return a Boolean of the result in the response.

3) Create an endpoint which accepts a List of Strings. The List of Strings represents the paths to n number of images stored in an S3 bucket. Analyze which images are identical in the List. Return a List of the paths to the identical images in the response.

## Requirements

For building and running the application you need:

- JDK 11
- Maven

For running the application you need to set up environment variables:
- AWS_ACCESS_KEY_ID (or AWS_ACCESS_KEY)
- AWS_SECRET_KEY (or AWS_SECRET_ACCESS_KEY)

## Running the application locally

One way is to execute the main method in the `CodingChallengeApplication` class from your IDE.

Alternatively you can use the Spring Boot Maven plugin:

```$xslt
mvn spring-boot:run
```
