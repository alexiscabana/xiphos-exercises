# Xiphos interview - Exercise 1

## Setup of `Poky` and `meta-raspberrypi`

1. Clone the Poky repository and checkout the honister version
```
$ mkdir ~/xiphos

$ cd ~/xiphos

$ git clone git://git.yoctoproject.org/poky

$ cd poky

$ git checkout honister
```

2. Add the Raspberry Pi BSP overlay in the poky repo, and make sure to be on the proper branch
```
$ cd ~/xiphos/poky

$ git clone git@github.com:agherzan/meta-raspberrypi.git

$ cd meta-raspberrypi

$ git checkout honister
```

3. Setup a directory for the build
```
$ cd ~/xiphos/poky

$ source oe-init-build-env build 
```
4. Add the RPi BSP layer in the list of available layers in the `~/xiphos/poky/build/conf/bblayers.conf` file :

```
BBLAYERS ?= " \
  /home/alexis/xiphos/poky/meta \
  /home/alexis/xiphos/poky/meta-poky \
  /home/alexis/xiphos/poky/meta-yocto-bsp \
  /home/alexis/xiphos/poky/meta-raspberrypi \
  "
```

5. Change the MACHINE variable in `~/xiphos/poky/build/conf/local.conf` to the one for the Raspberry Pi
```
MACHINE ??= "raspberrypi3"
```
---
## Creating the new `meta-bootgen-addon` layer
6. Create the boilerplate structure for the Xilinx bootgen layer. This automatically configures all recipes matching `meta-bootgen-addon/recipes-*/*/*.bb` to be included in the layer.
```
$ cd ~/xiphos/poky

$ bitbake-layers create-layer meta-bootgen-addon
```

You should see a `~/xiphos/poky/meta-bootgen-addon/conf/layer.conf` file.

7. Make sure the layer can be found locally by adding it in the `~/xiphos/poky/build/conf/bblayers.conf` file
```
BBLAYERS ?= " \
  /home/alexis/xiphos/poky/meta \
  /home/alexis/xiphos/poky/meta-poky \
  /home/alexis/xiphos/poky/meta-yocto-bsp \
  /home/alexis/xiphos/poky/meta-raspberrypi \
  /home/alexis/xiphos/poky/meta-bootgen-addon \
  "
```
8. Copy the `bootgen_0.0.1.bb` file (in this repo) in the following directory : `~/xiphos/poky/meta-bootgen-addon/recipes-core/bootgen/`

Note: You might need to create some directories.

9. Make sure the `bootgen` and `openssl` packages are included in the image by adding this line anywhere in `~/xiphos/poky/build/conf/local.conf`
```
IMAGE_INSTALL += "openssl bootgen"
```

---
## Building the image
10. You can now run `bitbake` to build the entire image. The process will run for several hours.
```
bitbake core-image-base
```

