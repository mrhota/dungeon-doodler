# Dungeon Doodler

Let's do some drawing! I've identified some simple drawing libraries.

- p5.js
  - already vaguely familiar with this through coding train youtube videos
  - also found [some nice cljs + p5.js demos](https://github.com/tom10d/clojurescript-p5js/tree/main) that I can crib!
  - found Quil! this uses Processing under the hood, so everything I learn from Coding Train videos will translate, roughly!
- paper.js
  - also seems nice
- rough.js

## figwheel

I have introduced Figwheel into my cljs development flow.

```
{:deps {com.bhauman/figwheel-main {:mvn/version "0.2.18"}}}
```

### My own html for figwheel development

Place any html you'd like to have Figwheel display when you start it up in the `resources/public/index.html` file. This lets me use a blank page with the structure I want instead of Figwheel's default (noisy) REPL page.

---

### builds

Builds are defined by edn files in the root directory.

- `dev.cljs.edn` defines a `dev` build
  - the file contains Clojurescript compiler configurations for the build
  - `:main` specifies the entrypoint

Figwheel can be directed to compile a build and open a repl for it like so:

```
clj -M -m figwheel.main -b dev -r
```

> Use the `clojure` command if you're _also_ using something like `rebel-readline`. Otherwise use `clj` because it provides its own terminal line reader. In my case, `rebel-readline` and Warp terminal don't seem to play well together.

Because we have an alias defined in `deps.edn` called `:fig`, we can shorten the command above to

```
clj -M:fig -b dev -r
```
