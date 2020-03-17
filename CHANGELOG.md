# Changelog

## Next release (master)

ATTENTION: This release comes with a few minor breaking changes.

* Bindings don't have value converters, anymore. Usually you need to store the raw field value and a separate conversion (if possible without errors), anyway. Use `autoRun` or `derived` to convert values.
* Added bindings for `CompoundButton` (replacing `CheckBox` bindings).
* `AutoRunner`'s and `autoRun`'s `onChange` callback now receives the `AutoRunner` as its first argument.
* Added unit tests.

## 0.5

Initial release.