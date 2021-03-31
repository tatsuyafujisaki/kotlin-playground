# Scope functions
A receiver is an object that invokes the method.

Function|How to reference receiver|Can receiver be nullable?|Return value
---|---|---|---
`apply`|`this` (optional)|yes|receiver (`this`)
`also`|`it`|yes|reciever (`it`)
`run`|`this` (optional)|yes|last expression
`with`|`this` (optional)|no|last expression
`let`|`it`|yes|last expression
