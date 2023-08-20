(defproject duckyuck/orchestra "2021.01.01-1"
  :description "Complete instrumentation for clojure.spec"
  :url "https://github.com/jeaye/orchestra"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1" :scope "provided"]
                 [org.clojure/clojurescript "1.10.773" :scope "provided"]
                 [org.clojure/spec.alpha "0.2.187" :scope "provided"]]
  :plugins [[lein-cloverage "1.1.2"]
            [lein-cljsbuild "1.1.8"]
            [lein-figwheel "0.5.20"]
            [com.jakemccrary/lein-test-refresh "0.24.1"]
            [lein-shell "0.5.0"]
            [lein-auto "0.1.3"]]
  :aliases {"test-cljs"
            ["do"
             ["cljsbuild" "once" "app"]
             ["shell" "node" "target/test.js"]]}
  :global-vars {*warn-on-reflection* true}
  :test-refresh {:quiet true
                 :focus-flag :refresh}
  :source-paths ["src/clj/" "src/cljc/" "src/cljs/"]
  :cljsbuild {:builds {:app
                       {:source-paths ["src/cljs/"]
                        :compiler
                        {:optimizations :none
                         :pretty-print false
                         :parallel-build true
                         :output-dir "target/test"
                         :output-to "target/test.js"}}}}
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[expound "0.8.5"]
                                  [lein-doo "0.1.11"]]
                   :source-paths ["test/clj/" "test/cljc/"]
                   :cljsbuild {:builds {:app
                                        {:source-paths ["test/cljs/" "test/cljc/"]
                                         :compiler
                                         {:main orchestra-cljs.test
                                          :target :nodejs}}}}}})
