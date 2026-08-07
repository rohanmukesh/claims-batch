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
