(defproject simple-compiler "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/algo.monads "0.1.5"]]
  :main ^:skip-aot simple-compiler.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
