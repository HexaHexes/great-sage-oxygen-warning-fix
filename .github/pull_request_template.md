## Changeset

<!-- One logical change per PR. Split unrelated work into separate PRs. -->

## Checklist

- [ ] This PR contains **one** logical changeset
- [ ] `./gradlew build` passes locally (this mod has no GameTests; compile is the gate)
- [ ] Change is documented; README updated if user-facing
- [ ] This PR adds a feature, or changes one `README.md` already describes,
      and updates `README.md` in this PR
- [ ] No logs, `run/`, or `crash-reports/` committed

## Breaking change

<!-- Tick only if this PR breaks backwards compatibility. CI uses this label to
     require that the next beta or stable version bump raises major or minor. -->

- [ ] This change is **breaking** — apply the `breaking` label before merge

## Version bump

<!-- Only complete this section if gradle.properties is changing. -->

- [ ] This PR bumps `mod_version` **and nothing else**
- [ ] New version is a clean bump (e.g. `1.0.0`) — releases as stable
- [ ] New version has a `-beta`/`-rc` suffix — releases as beta
- [ ] New version has an `-alpha` suffix — releases as alpha; other changes permitted
