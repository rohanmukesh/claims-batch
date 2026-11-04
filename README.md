# claims-batch

Claims demo service for Spring Response / Hub Business Application walkthrough.

- Spring Boot `2.6.15`
- Target CF org/space: `acme-insurance / claims-prod` (or `rohan-home-lab / claims-prod` until org exists)

## Local

```bash
mvn -q spring-boot:run
```

## Publish SBOM to Hub (after Hub repository connection env vars are set)

```bash
cf repo publish-sbom -b mvn -p .
```

See Broadcom docs: https://techdocs.broadcom.com/us/en/vmware-tanzu/platform/tanzu-cf-cli/10-4/t-cf-cli/cmds-repo_publish-sbom.html

## TrueSource Spring Maven repositories

The `truesource` Maven profile configures:

- Spring Enterprise tooling, recipes, extensions, and patch releases:
  `https://packages.broadcom.com/artifactory/spring-enterprise/`
- Rebuilt Spring transitive dependencies:
  `https://packages.broadcom.com/artifactory/tanzu-spring-dependencies/`

The dependency repository is checked before Spring Enterprise for dependencies
available from both. Maven Central remains available as a fallback. Enabling
these repositories does not change the dependency versions declared by this app.

### Build through the shared Nexus instance

Use the read-only Nexus Maven settings supplied by your repository administrator:

```bash
mvn -s /path/to/nexus-settings.xml -Ptruesource clean verify
```

For the current lab, the Maven group is
`https://hub.local:8444/repository/truesource-maven/`. The build machine needs
network access to that address and JVM trust for the lab's HTTPS certificate.
The settings file and credentials stay outside this repository.

### Build directly against Broadcom

Set `BROADCOM_EMAIL` and `BROADCOM_REGISTRY_TOKEN` in your shell or secret store,
then run:

```bash
mvn -s .mvn/truesource-settings.xml -Ptruesource clean verify
```

The checked-in settings contain environment-variable references. Store no real
credentials in this file or in `pom.xml`.

### GitHub Actions

Set the repository Actions secrets `BROADCOM_EMAIL` and
`BROADCOM_REGISTRY_TOKEN`. Maven CI enables the `truesource` profile when both
are available. GitHub-hosted runners use the public Broadcom endpoints because
the lab Nexus hostname is on a private network.

Without those secrets, CI uses the default Maven repositories; builds that
already require commercial-only versions still need Broadcom credentials.
