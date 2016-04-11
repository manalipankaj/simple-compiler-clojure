(ns simple-compiler.core
  (:require [clojure.string :as str])
  (:gen-class))

(:use '[clojure.string :only trim])

;;;  Tokenize  ;;;;
(defn is-paren [input]
  (let [chr (nth (str/trim input) 0)]
   (when (or (= chr \( ) (= chr \)))
     [{:type "paren" :value chr} (subs input 1)])))

(defn is-name-number [input]
  (loop [in input acc ""]
    (let [n (nth in 0)]
      (if (or (= n \space) (= n \() (= n \)))
        (if (re-matches #"\d+" acc) [{:type "number" :value acc} in] [{:type "name" :value acc} in])
        (recur (subs in 1) (str acc n))))))

(defn tokenizer [input]
  (loop [current input accum []]
    (cond
     (= 0 (count current)) accum
     (not (nil? (is-paren current))) (let [x (is-paren (str/trim current))] (recur (str/trim (last x)) (conj accum (first x))))
     (not (nil? (is-name-number current))) (let [x (is-name-number (str/trim current))] (recur (str/trim (last x)) (conj accum (first x))))
     :else accum)))
;;;  End Tokenizer ;;;;;;

;;; AST ;;;;
;; (defn make-expresion-node [input]
;;   )

;; (defn ast [input]
;;   (loop [token input accum {:type "Program"} init true]
;;     (if init
;;       (recur token (assoc accum :body []) false)
;;       (cond
;;        (= "name" (get (first token) :type)) 
;;        (recur (drop 1 token) (assoc accum :body (conj (get accum :body) (make-expresion-node (token)))) init)
       
;;        ))))

;; (defn parser [input]
;;   (def ast {:type "program" :body []})
;;   (loop []
;;     (when (< current (count input))
;;       (assoc ast :body (conj (get ast :body) (make_tree input))))))

(declare walk)
(defn parser [tokens]
  (def current (atom 0))
  (loop [accum []]
    (println "returned "accum)
    (if (< @current (count tokens))
      (recur (conj accum (walk tokens current)))
      accum)))

;;; AST ;;
(defn walk [tokens current]
  (let [token (nth tokens @current)]
    (cond
     (= "number" (get token :type)) (do (swap! current inc) {:type "NumberLiteral" :value (get token :value)})
     (= \( (get token :value)) 
     (do 
       (swap! current inc)
       (let [token1 (nth tokens @current)]
         (println "token1" token1)
         (def node (atom {:type "CallExpression" :callee {:type "Identifier" :name (get token1 :value)} :arguments []}))
         (def args (atom []))
         (swap! current inc)
         (loop [token3 (nth tokens @current) accum @args]
           ;; (swap! current inc)
           (println "token3" token3)
           (println "accum" accum)
           (if (= \) (get token3 :value))
             accum
             (recur (nth tokens @current) (swap! args conj (walk tokens current)))))
          (swap! current inc)
         @nodes)))))

(defn )
