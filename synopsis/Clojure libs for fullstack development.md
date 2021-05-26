This is a very good question with no easy answer. Clojure is used to develop mobile apps, APIs, IOT, data pipelines (and much more). In most cases, you'd be using the industry standard libraries, like React for UI dev and Kafka for queues.

If I were to start learning again, I'd divide my path in two steps:  
**Thought process**

-   Functional concepts (same for most functional languages)
    
-   Immutability
    
-   Interop (once you get this, you can use and JS/ Java library in Clojure)
    
-   Syntax and standard library
    
-   Live coding with the REPL (using Cider/ Calva/ Cursive/ Conjure)
    
-   Async model
    
-   Rich Hickey's and Stuart Halloway's talks at the minimum
    

**Specialisation**

Once you are comfortable with the basics, you can start to specialise:

-   For writing UI (React) try Reagent and Re-Frame
    
-   For immutable databases look at Datascript/ Datomic/ Datahike or Crux (I'd recommend Datascript because it is the easiest to setup)
    
-   For DI check out Mount, Integrant and Component
    
-   For Graphql, there is Lacinia
    
-   For REST APIs: Pedestal, Reitit, Bidi
    
-   For batteries included framework: Luminus, Hoplon
    

I'm sure there are more great libs, but the ones listed above are the ones I use the most.