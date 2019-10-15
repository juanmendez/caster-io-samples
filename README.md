# Gradle Plugin Basics

This fork from tutorial from [caster.io](https://caster.io/lessons/creating-a-gradle-plugin) is intended to be updated and also show the tutorial using `kotlin` as well.

Branches:
- groovy/00.pluginBasics (using androidX, and kotlin)
- kts/00.pluginBasics

- groovy/01.internalGradlePlugin 
  - `buildSrc` module was missing `build.gradle` causing internal plugin not to be found`
- kts/01.internalGradlePlugin
  - there were several irregularities from the `Groovy` demo which I needed to fix for `kotlin` version to work

- groovy/02.externalGradlePlugin
- kts/02.externalGradlePlugin (this one was a tough one to turn it all from Groovy, specially `project.evaluate()` is no longer available
