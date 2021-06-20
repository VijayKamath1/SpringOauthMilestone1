This is an example for creating a OAuth server to create JWT tokens and sign with a private key


a) Authorization server is configured to use JWTs as token implementation and sign the JWT with a private key. Key pair is generatedand set the authorization server to use it.

b) In-memory user management is done.

c) In-memory client management is done.

d)integration tests to prove that the endpoint used to issue the token works correctly.
