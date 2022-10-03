import 'dart:io';

import 'package:flutter/material.dart';
import 'package:image_cropper/image_cropper.dart';
import 'package:image_picker/image_picker.dart';
import 'package:pentagon/generated/l10n.dart';

class PickImage {
  static Future<File?> pickImage(BuildContext context) async {
    final local = S.of(context);
    final theme = Theme.of(context);
    final picker = ImagePicker();
    final cropper = ImageCropper();

    ImageSource? source = await showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text(S.of(context).source),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(context).pop(),
            style: TextButton.styleFrom(
              backgroundColor: Theme.of(context).errorColor,
              foregroundColor: Colors.white,
            ),
            child: Text(S.of(context).cancel),
          ),
          TextButton(
            onPressed: () => Navigator.of(context).pop(ImageSource.gallery),
            child: Text(S.of(context).gallery),
          ),
          TextButton(
            onPressed: () => Navigator.of(context).pop(ImageSource.camera),
            child: Text(S.of(context).camera),
          ),
        ],
      ),
    );

    if (source != null) {
      XFile? pickedImage = await picker.pickImage(source: source);
      if (pickedImage != null) {
        CroppedFile? croppedImage = await cropper.cropImage(
          uiSettings: [
            AndroidUiSettings(
              toolbarTitle: local.cropImage,
              toolbarWidgetColor: Colors.white,
              toolbarColor: theme.colorScheme.primary,
              activeControlsWidgetColor: theme.colorScheme.secondary,
            )
          ],
          sourcePath: pickedImage.path,
          aspectRatio: const CropAspectRatio(ratioX: 1, ratioY: 1),
          compressQuality: 70,
        );
        if (croppedImage != null) {
          return File(croppedImage.path);
        }
      }
    }
    return null;
  }
}
