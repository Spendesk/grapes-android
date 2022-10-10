# Grapes-Android 🍇
[![Grapes-Android](https://jitpack.io/v/Spendesk/grapes-android.svg)](https://jitpack.io/#Spendesk/grapes-android)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Spendesk/grapes-android/graphs/commit-activity)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)
[![GitHub Issues](https://img.shields.io/github/issues/Spendesk/grapes-android.svg)](https://Github.com/Spendesk/grapes-android/issues)
[![GitHub stars](https://img.shields.io/github/stars/Spendesk/grapes-android.svg?style=social&label=Star&maxAge=2592000)](https://GitHub.com/Spendesk/grapes-android/stargazers/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Grapes android is a design system library created and used within Spendesk android application.

Ownership of standards & best practices between design and engineering are often unclear or anecdotal.
As Engineers and designers, we wanted to centralize standard components, documenting the bits and pieces, small and large, that make up Spendesk's great experience.
That is why we created Grapes.
We are glad to share our design system to the open source world and hope that other companies and developers will have the same enthusiasm.
This library will be subject to a lot of evolution as the majority of the components used were not created within the Grapes scope.
An application containing Grapes components, GrapesSamples, is also side developed in order to provide the best experience while discovering the design system.

Please, enjoy, share, and contribute to Grapes.


## Installation

### Use Gradle

Add this in your root build.gradle:

```bash
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Add the dependency to your application module:
```bash
implementation 'com.github.Spendesk.grapes-android:grapes-android:$grapes_version' // View only dependency
implementation 'com.github.Spendesk.grapes-android:grapes-android-compose:$grapes_version' // Compose only dependency
```

The latest Grapes version is: [![Grapes-Android](https://jitpack.io/v/Spendesk/grapes-android.svg)](https://jitpack.io/#Spendesk/grapes-android)

## Contributing

Please take a look at our [contributing](./CONTRIBUTING.md) guidelines if you're interested in helping!

## License

Grapes-Android is released under the Apache-2.0 license.
See [LICENSE](./LICENSE) for details.
