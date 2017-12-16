(defproject drd "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:source-paths ["src" "tool-src"]}
             :uberjar {:aot :all}}
  :aliases {"brevity" ["run" "-m" "brevity.core/handle-commands"]}
  :main ^:skip-aot drd.core
  :dependencies [[org.clojure/clojure "LATEST"]
                 [org.immutant/web "LATEST"]
                 [ring/ring-core "LATEST"]
                 [ring/ring-devel "LATEST"]
                 [ring/ring-json "LATEST"]
                 [compojure "LATEST"]
                 [buddy/buddy-auth "LATEST"]
                 [buddy/buddy-sign "LATEST"]
                 [caesium "LATEST"]
                 [io.forward/yaml "LATEST"]
                 [camel-snake-kebab "LATEST"]
                 [org.clojure/clojurescript "LATEST"]
                 [hiccup "LATEST"]
                 [reagent "LATEST"]]
  :clean-targets ["static/development/js"
                  "static/release/js"
                  "static/development/index.js"
                  "static/development/index.js.map"
                  "out"
                  "target"]
  :plugins [[lein-cljsbuild "LATEST"]]
  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["cljs-src"]
                :compiler
                {:output-to "static/development/index.js"
                 :source-map true
                 :output-dir "static/development/js"
                 :optimizations :none
                 :main drd.cljs.core
                 :asset-path "development/js"
                 :cache-analysis true
                 :pretty-print true}}
               {:id "release"
                :source-paths ["cljs-src"]
                :compiler
                {:output-to "static/release/index.js"
                 :source-map "static/release/index.js.map"
                 :externs []
                 :main drd.cljs.core
                 :output-dir "static/release/js"
                 :optimizations :advanced
                 :pseudo-names false}}]}) 
