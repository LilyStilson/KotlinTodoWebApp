# Compose Multiplatform Todo
Todo web application implemented in Kotlin and Compose Multiplatform, targeting Kotlin/WASM.

[Try it out](https://kotlinwasmtodo.pages.dev/)

## Features
- Adding tasks
- Editing tasks
- Removing tasks
- Marking tasks as done
- Filtering tasks by status

## Known issues
Note: *Those issues are related to the alpha state of Compose Multiplatform for WASM.*
- On displays with scaling canvas' contents are not scaled properly
- On mobile, the keyboard does not open when focusing on a text field

## Automatic deploy
This repository is configured to automatically build the production distribution, push it to `release` branch and deploy it to [Cloudflare Pages](https://pages.cloudflare.com/).

## Running web application
1) Clone the repository
2) `cd KotlinWasmTodo`
3) `./gradlew wasmJsBrowserRun`

## Compiling production bundle
1) Clone the repository
2) `cd KotlinWasmTodo`
3) `./gradlew wasmJsBrowserDistribution`

