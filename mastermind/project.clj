(defproject mastermind "0.1.0-SNAPSHOT"
  :description "First project in declaratif programming, mastermind"
  :url "http://project.com/3I020"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot mastermind.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
