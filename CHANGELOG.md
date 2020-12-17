## Version 2.1.2

This patch fix features some bug fixes that are required for the release of my new Chameleon mod.

* Added `RenderLightmap` utility class that allows setting hurt colors
* Added `ReflectionUtils.registerResourcePack(IResourcePack)` to register resource custom packs
* Changed scrolling speed in mod options GUI to `51` pixels per mouse wheel scroll 
* Fixed `GuiElement.canBeSeen()` skipping first parent when checking for visibility
* Fixed wrong order of math expression parsing resulting into wrong calculations

## Version 2.1.1

This tiny update that provides some changes for Blockbuster.

* Added GuiCanvasEditor class, which provides basic layout for image-like editors

## Version 2.1

This big update adds a lot of useful code (including WAV file parsing, loading and rendering), a handful of GUI element tweaks and new multi-skin features!

* Added multi-skin editor, which includes new options:
    * Scale - scales up and down a skin
    * Shift - moves around a skin on the canvas
    * Scale to largest - allows easily to scale to the largest multi-skin in the list
    * Color - allows to multiply every pixel by this color, allowing hue shifting grayscale textures
    * Pixelate - filter that allows to pixelate a texture
    * Erase - option that allows to erase everything below using the current skin's opaque pixels as erase mask
* Added list sorting to multi-skin
* Added an option to asynchronously load multi-skin skins (enabled by default)
* Added WAV parsing and waveform rendering
* Added favorite and recent color palette GUI elements to color picker
* Added arrow up and down keystrokes to increment and decrement, respectively, a value in trackpad fields (shift, ctrl and alt affect the value that is getting added or subtracted, suggested by Joziah2)
* Added an option to disable increment button on trackpad fields
* Fixed relative value editing of keyframes having horrible results
* Fixed crash when emptying the text field in texture picker (reported by Tossler)
* Fixed trackpad fields not indicating that value wasn't accepted after manually inputting out of range (reported by Tossler)
* Fixed alpha colors not showing transparency when borders are enabled
* Fixed dashboard not closing properly panels after switching to another GUI (instead of closing through Escape key) causing some panels to bug (reported by Chunk7)

## Version 2.0.3

This minor patch features enormous improvements to keyframe editing GUIs.

<a href="https://youtu.be/6eil_zvv1KI"><img src="https://img.youtube.com/vi/6eil_zvv1KI/0.jpg"></a> 

* Added value editing through the value field in the dope sheet editor
* Added bezier handles editing in tick and value fields when selecting them (instead of showing tick and value of the keyframe)
* Added multi-selection of keyframes in graph/dope sheet GUI elements:
    * You can select them by Shift + Dragging an area in which all the keyframes will be selected
    * You can select individual keyframes by Shift + clicking on them to select not selected keyframe or deselect selected keyframe
    * You can move multiple-selected keyframes or its handles (depending on selection mode) by editing tick or value fields or dragging
    * You can also duplicate multiple selected keyframes using the same old way of holding alt while clicking else where to duplicate
* Added context menu to graph/dope sheet elements to:
    * Remove selected keyframes
    * Switch selection between keyframe or its handles
* Added new interpolations (with in, out, in/out easings): back, elastic and bounce
* Fixed last keyframe's right bezier handle being editable

## Version 2.0.2

This is a small quick patch which features a couple of neat GUI tweaks.

* Added a feature to shift the mouse cursor to the opposite side of the screen when dragging trackpad fields
* Added tracking of how many times keystrokes were pressed recently
* Added evaluating of math expressions within trackpad fields upon pressing Alt + Enter when focused
* Changed trackpad fields to use horizontal mouse offset, instead of distance
* Changed zooming in/out in model renderers be more adaptive (zoom factor changes depending on current scale, which makes it faster to zoom in and out)
* Fixed crash with index out of bounds with list elements (reported by Lycoon)
* Fixed bug with math expressions having `(` in the beginning and `)` in the end of the expression being invalid
* Moved keyframe code from Aperture (for future features)

## Version 2.0.1

This is a small quick patch.

* Added more characters which are allowed to be input into text fields with filename constraint (`[`, `]`, `!`, `@`, `#`, `$`, `%`, `^`, `&`, `(` and `)`)
* Fixed yellow highlight in the texture picker
* Fixed mouse wheel scrolling going through when scrollbar reaches the end in the texture picker (suggested by Lucatim)
* Updated Chinese strings for 2.0 (thanks to Chunk7, KuenYo_ and H2SO4GepaoAX)

## Version 2.0

This enormous update drastically improves the GUI system that is used in my mods, as well as adding its own configuration system, and different APIs to make it easier to develop GUI stuff. It's also allows you to slightly personalize the GUI.

<a href="https://youtu.be/96_VnqywyRY"><img src="https://img.youtube.com/vi/96_VnqywyRY/0.jpg"></a> 

* Added configuration system
* Added Dashboard GUI from Blockbuster mod
    * Added `Numpad*` keybinds to open a specific panel
* Added Icon API to ease texture coordinate hell
* Added `GuiUtils.openWebLink(String)`
* Added `MatrixUtils` class from Blockbuster mod
* Added `Timer` class to keep mark and keep track of timed operations
* Added config options:
    * Primary color — sets the default color for all sorts of GUI elements, including selection and active colors
    * Button borders — toggles black borders around button elements
    * Checkbox instead of toggle element — toggles toggle element rendering as a checkbox
    * Model grid — toggles grid rendering instead of a grass block in model renderer
    * GUI scale — allows to change the GUI scale of McLib's GUIs without changing Minecraft's GUI scale
    * Mouse cursor rendering — toggles mouse cursor rendering (moved from Aperture mod)
    * Keystrokes rendering — toggles pressed keystrokes rendering
    * Background image and background color — allows to set a custom image background or background color
    * Scrollbars options — allow you to customize appearance of scrollbar
    * Max. packet size — vanilla tweak option which allows to increase the limit of Minecraft's network messages
* Fixed `Interpolations.envelope` going below `0` when outside of envelope's range
* Fixed texture picker messing up the resource location in the root
* Improved math expression parser:
    * Added more math functions: 
        * `round(x)` — round `x` to nearest integer
        * `ceil(x)` — round `x` to upper integer
        * `trunc(x)` — truncate `x`, floor `x` when `x` is positive, and ceil `x` when it's negative
        * `max(a, b)` — return a bigger value between `a` and `b`
        * `min(a, b)` — return a smaller value between `a` and `b`
        * `exp(x)` — e constant to the power of `x`
        * `ln(x)` — natural logarithm of `x`
        * `sqrt(x)` — square root of `x`
        * `mod(x, d)` — get remainder of `x` divided by `d`, equivalent to `x % d`
        * `pow(x, p)` — `x` to the power of `p`, equivalent to `x^p`
        * `lerp(a, b, x)` — linearly interpolate between `a` to `b` using `x` as a factor
    * Added ternary operator support, for example `0 ? 1 : 5 = 5`, `1 ? 4 : 2 = 4` or `x >= 0 ? 5 : -5`, equals `5` when `x` is positive, and `-5` if `x` is negative
    * Added boolean comparison operators which return `1` if condition is met, or `0` otherwise (these operators are also have the highest precedence): 
        * `&&` — `1` if both operands are non-zero
        * `||` — `1` if only one operand non-zero
        * `<` — `1` if left operand is less than second
        * `<=` — `1` if left operand is less or equal to second operand
        * `>=` — `1` if left operand is greater or equal to second operand
        * `>` — `1` if left operand is greater to second operand
        * `==` — `1` if left operand equals to second
        * `!=` — `1` if left operand isn't equal to second
    * Added `!` operator which negates an expression, i.e. if expression is non-zero, then it returns `0`, otherwise if expression is `0` it returns `1`, for example: `!(5 + 2) = 0` and `!(2 - 2) = 1`
* Improved `GuiDraw.scissor` to support nested scissor test
* Refactored fully the GUI system:
    * Added current focused element to fix multiple selected text fields bug
        * Added an ability to Tab betweeen input fields
        * Added `Esc` keybind to unfocus the element when focused
    * Added tooltip support to every element
    * Added support for horizontal scroll in scroll element
    * Added context menu API
    * Added keybind support for individual GUI elements
        * Added `F9` keybind to any GUI screen to view all available keybinds
    * Added color picker element
    * Changed track pad element:
        * Added increment buttons to incremeent by one
        * Changed dragging from vertical distance to horizontal distance
        * Changed from `float` to `double` for better precision
        * Removed label option
    * Changed modal elements to remove themselves from parent instead of unsetting the delegate
    * Fixed labels staying the same after changing to a different language
    * Improved list element:
        * Added label-value lists
        * Added multi-selection support (shift + click adds selects an additional element)
        * Added drag-n-drop sorting support
        * Added direct filtering of elements support
        * Added interpolations list from Blockbuster mod
        * Added support for horizontal lists
    * Refactored resizers to support many new placement features, including automatic layout resizers for responsive row, column and grid placement of the elements
    * Refactored inventory and slot elements
    * Refactored button elements:
        * Added for circulate button element to right click for previous value
    * Refactored model renderer:
        * Added picking code from Blockbuster
        * Changed the navigation within to orbit style
        * Fixed `update` method to be called correctly regardless of framerate

## Version 1.0.4

This is a patch update which reworks file tree system, improves texture picker GUI and a couple of other neat features.

* Added key repeating (`Keyboard.enableRepeatEvents(true);`) (suggested by Tschipp)
* Added "Open folder" to texture picker to open current folder 
* Added `Interpolations.envelope()` methods which allow to easily make fade in/out factor calculations based on given time value, and durations of fade in, sustain and fade out
* Added background option for list elemenet
* Added refreshing of current folder in texture picker
* Added `IResourceTransformer` to allow fixing any resource locations created via `RLUtils`
* Added keyboard control to texture picker. You can now use arrows up/down to go up and down in the list, shift + arrow up/down to jump in the beginning/end of the list, type in the name to select the closest matching and enter to select the file/enter the folder
* Changed the way file tree system works to allow lazy loading (instead of full tree generation) and check for changes

## Version 1.0.3

A small patch update which is accompanied by Aperture 1.3.2 release.

* Added max values for width and height of `Resizer` class
* Added scrollable base GUI element
* Added support for relative width and height for `Resizer` class
* Added `double` variants of interpolation functions
* Added `Interpolation` enum
* Fixed issue with JSON multi resource location

## Version 1.0.2

A small patch update that features a couple of fixes, and one small feature.

* Add `random([range/min], [max], [seed])` function to math expression builder
* Change clicking on `+` in multi-skin menu to select the added resource location
* Fix multiple `../` in the texture picker (reported by mr wolf)
* Fix crash with registering texture when a multi-skin child returns a `null`

## Version 1.0.1

Second update. Mostly provides new code for Blockbuster, Aperture and Metamorph.

* Added a core mod which adds a handler in resource manager to support texture multi resource locations (i.e. multi-skin feature)
* Added texture picker GUI from Blockbuster and file tree API
* Added panel base GUI class (which allows to create multi-paneled GUIs)
* Extracted dummy entity and model renderer GUI from Blockbuster
* Extracted interpolation code from Aperture
* Extracted core modding utils from Blockbuster
* Extracted texture map reflection code from Blockbuster
* Fixed alpha in modals GUI which caused font not to render properly (reported by ycwei982)

## Version 1.0

Initial release. This bad boy contains so much useful code.

* Extracted math expression code from Aperture
* Extracted GUI framework code from Blockbuster
* Extracted Ernio's network base code from Blockbuster