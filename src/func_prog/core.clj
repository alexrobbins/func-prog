(ns func-prog.core)
;; Functional Programming in Clojure

;; Functions to cover:
#()
apply
identity
complement
iterate
repeat
repeatedly
constantly
map
partial
comp
map-indexed
reduce

;;-----

;; Beautiful code is as simple as possible.
;; Minimal, readable, idiomatic.

;;-----

;; Anonymous function syntax:

#(inc %)
(#(+ %1 %2) 1 2)

;;-----

apply

;; Someone gives you a list of numbers they want added.

(+ [1 2 3 4])

(apply + [1 2 3 4])

;; Advanced mode:
(apply + 1 2 [1 2 3 4])

;;-----

identity

;; Return exactly what was passed in.
;; Useful when you already have the values you want for a filter.

(filter identity [true false false nil :a])

;;-----

complement

;; The HOF equivalent of not.

(not true)
(complement true)
((complement identity) true)

((complement string?) "This is a string")

;;-----

iterate

;; Keep calling a function on its result, to make a lazy sequence.

;; Make sure you limit the output you consume, otherwise it'll never stop.

(take 10 (iterate inc 0))
(take 10 (iterate #(* -1 %) 1))
(take 4 (iterate #(list 'f %) 'x))

;;-----

repeat

;; Produce a lazy sequence of the input, possibly limited.

(take 10 (repeat :a))
(repeat 10 :a)

;;-----

repeatedly

;; Calls a function over an over, returning the list of results.
;; This doesn't really make sense unless the function has side effects.
(repeatedly 3 #(java.util.Date.))

;;-----

constantly

;; Returns a function that constantly evaluates to whatever argument you passed.

(constantly 1)
(repeatedly 5 (constantly 4))

;;-----

map

;; You have a function you want to apply to a sequence of inputs.

(map inc [1 2 3 4 5])

;;-----

partial

;; Useful when you only have some of the arguments now.

(partial + 1)
((partial + 1) 1)

(map (partial + 1) [1 2 3 4])

;;-----

comp

;; You have two functions that you almost always need to use
;; together.

(comp keyword :name)

((comp keyword :name) {:name "Clojure" :type "Language"})

;; Works well with `map`

(map (comp name second) [[:a :b] [:c :d] [:e :f]])


;;-----

map-indexed

;; Use it when you need to know how far into the seq you are.

(map-indexed #(vector %1 %2) [:a :b :c])

;;-----

reduce

;; Use it when you want to reduce a sequence to a single value.

(reduce + [1 2 3 4])
(reduce + 5 [1 2 3 4])
(reduce merge [{:a 1} {:b 2} {:c 3}])