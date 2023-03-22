# Flux create vs Generate

| Create                                                                                        | Generate                                             |
|-----------------------------------------------------------------------------------------------|------------------------------------------------------|
| Accept Consumer<FluxSink<T>>                                                                  | Accept Consumer<SynchoronousSink<T>>                 |
| Invoked only once                                                                             | invoked again and again based on downstream demand   |
| can emit 0...N immediately                                                                    | can emit only one element at the time                |
| publisher not be aware of downstream processing speed.<br/> need to provide OVERFLOW Strategy | publisher produce element based on downstream demand |
| Thread-Safe                                                                                   | NA                                                   |
| avl methods: requestedFromDownStream(), isCancelled()                                         | NA                                                   |
