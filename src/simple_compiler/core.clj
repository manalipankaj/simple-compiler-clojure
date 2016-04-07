(ns simple-compiler.core
  (:use [clojure.algo.monads :only [domonad with-monad state-t maybe-m
  fetch-state set-state m-seq m-plus m-result]])
  (:require [clojure.string :as str])
  (:import (java.io BufferedReader FileReader)))
  (:gen-class))

(:use '[clojure.stirng :only trim])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World! (add 2 (subtract 4 2)) "))

(def re-num #"d+")
(def re-id-start #"w_.$:")
(def re-id (re-pattern (str "[" re-id-start "][" re-id-start #"d]*")))

;;;  Tokenize  ;;;;
(defn is-paren [input]
  (let [chr (nth (trim input) 0)]
   (when (or (= chr \( ) (= chr \)))
     [{:paren chr} (subs input )])))

(defn is-number [input]
  (let [wrds ((subs (trim input) 1 (.indexOf input \space)))]))

()

(defn tokenizer [input]
  )

;;;  End Tokenizer ;;;;;;
