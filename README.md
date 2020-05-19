# Versioning
Study for versioning proposal.

# Goals
* Create an integration services versioning process that are simple to use, easily evolvable and reliable
* Allow loosely coupled evolution of services and clients
* Reduce time and cost to prod
* Turn our clients’ lives easier

# Premises
* Only one running service
 * No version number in the URL
* Rely on infrastructure tools
* Clients only need to know major versions
  * Version to use is decided at design time
* Services serve consumers
  * Consumers are clients and our services must serve them
* Short deployment cycle
  * Faster time to prod, easy version fix and rollback
* Adhere to community standards

# Concepts
* API
  * Set of services
  * Contract
  * Automates an specific business needs
  * Consumed by software components
  * Collection of swagger files with related objects
  * Well defined vocabulary (glossary)
* Service
  * Implements business solution
  * Composed of interface and code
    * Interface describes how to call and get the response
    * Code is the solution implementation
* HTTP Service
  * Client-server, stateless, layered, cacheable and with uniform interface software component
  * Service that relies on the HTTP application protocol and its specification
  * Composed of URIs and methods
* REST Service
  * HTTP Service that controls resource status
* Composed Service
  * Orchestrate one or more services
  * A verb for the service name often defines a composed service

# Proposal
* Only one running service in production
* One URL per service
* Clients only know major versions
* Clients inform the version they know when calling an operation
* Version operation, not service
* Version information in Content-Type: and Accept: header parameters
* Swagger files treated as code


