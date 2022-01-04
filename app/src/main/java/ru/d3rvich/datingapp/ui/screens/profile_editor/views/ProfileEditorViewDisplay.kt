package ru.d3rvich.datingapp.ui.screens.profile_editor.views

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.d3rvich.datingapp.R
import ru.d3rvich.datingapp.domain.entity.DateEntity
import ru.d3rvich.datingapp.domain.entity.ProfileEntity
import ru.d3rvich.datingapp.domain.utils.calculateFateNumber
import ru.d3rvich.datingapp.ui.common.clearFocusOnClick
import ru.d3rvich.datingapp.ui.common.clearFocusOnKeyboardDismiss
import ru.d3rvich.datingapp.ui.constants.Personalities
import ru.d3rvich.datingapp.ui.constants.Zodiac
import ru.d3rvich.datingapp.ui.mappers.toDateEntity
import ru.d3rvich.datingapp.ui.mappers.toLocalDate
import ru.d3rvich.datingapp.ui.model.DateWithoutYearUiModel
import java.time.LocalDate

@ExperimentalMaterialApi
@Composable
fun ProfileEditorViewDisplay(profile: ProfileEntity?, onSaveProfile: (ProfileEntity) -> Unit) {
    var name by rememberSaveable {
        mutableStateOf(profile?.name ?: "")
    }
    var city by rememberSaveable {
        mutableStateOf(profile?.city ?: "")
    }
    var birthday by rememberSaveable {
        mutableStateOf(profile?.birthday)
    }
    var description by rememberSaveable {
        mutableStateOf(profile?.description ?: "")
    }
    var fateNumber by rememberSaveable {
        mutableStateOf(profile?.fateNumber ?: 0)
    }
    var zodiacId by rememberSaveable {
        mutableStateOf(profile?.zodiacId ?: -1)
    }
    var socionicTypeNumber by rememberSaveable {
        mutableStateOf(profile?.socionicTypeNumber ?: 0)
    }
    var personalitiesNumber by rememberSaveable {
        mutableStateOf(profile?.personalitiesNumber ?: 0)
    }
    var imageLink by rememberSaveable {
        mutableStateOf(profile?.imageLink ?: "")
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageLink = uri?.toString() ?: imageLink
        }
    )
    val scrollState = rememberScrollState()
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(dialogState = dialogState, buttons = {
        positiveButton(stringResource(id = R.string.ok))
        negativeButton(stringResource(id = R.string.cancel))
    }) {
        datepicker(
            initialDate = birthday?.toLocalDate() ?: LocalDate.now(),
            title = stringResource(id = R.string.select_date),
            yearRange = IntRange(1900, LocalDate.now().year)
        ) { localDate: LocalDate ->
            birthday = localDate.toDateEntity()
            fateNumber = localDate.toDateEntity().calculateFateNumber()
            val date = DateWithoutYearUiModel(localDate.dayOfMonth, localDate.monthValue)
            zodiacId = Zodiac.values().indexOf(Zodiac.findZodiacByDate(date))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (imageLink == "") {
            Button(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 60.dp)
                    .size(60.dp),
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_a_photo_24),
                    contentDescription = stringResource(id = R.string.select_photo)
                )
            }
        } else {
            val bitmap: Bitmap = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                MediaStore.Images.Media.getBitmap(
                    LocalContext.current.contentResolver,
                    Uri.parse(imageLink)
                )
            } else {
                val source = ImageDecoder.createSource(
                    LocalContext.current.contentResolver,
                    Uri.parse(imageLink)
                )
                ImageDecoder.decodeBitmap(source)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
                Box(modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, MaterialTheme.colors.surface)
                        )
                    )) {
                    TextButton(
                        onClick = { launcher.launch("image/*") },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        Text(text = stringResource(id = R.string.change_photo))
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clearFocusOnClick()
                .padding(horizontal = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.name),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .clearFocusOnKeyboardDismiss(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center),
                singleLine = true
            )
            Text(
                text = stringResource(id = R.string.city),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start
            )
            TextField(
                value = city, onValueChange = { city = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .clearFocusOnKeyboardDismiss(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center),
                singleLine = true
            )

            Text(
                text = stringResource(id = R.string.birthday),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start
            )
            TextField(
                value = if (birthday != null) birthday.toString() else "",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { dialogState.show() },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current)
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    IconButton(onClick = { dialogState.show() }) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                    }
                },
                enabled = false
            )
            Text(
                text = stringResource(id = R.string.description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clearFocusOnKeyboardDismiss(),
                placeholder = {
                    Text(text = stringResource(id = R.string.describe_yourself))
                }
            )

            Text(
                text = stringResource(id = R.string.zodiac_sign),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )
            val zodiacText = if (zodiacId != -1) {
                val sign = Zodiac.values()[zodiacId]
                stringResource(id = sign.stringRes)
            } else {
                stringResource(id = R.string.you_have_to_enter_birthday)
            }
            TextField(
                value = zodiacText,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current)
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center),
                enabled = false
            )

            Text(
                text = stringResource(id = R.string.fate_number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )
            TextField(
                value = fateNumber.toString(),
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current)
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center),
                enabled = false
            )

            // TODO: 13.12.2021 Неужели соционический тип дублирует персоналити?!
            Text(
                text = stringResource(id = R.string.socionic_type),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )
            TextField(
                value = socionicTypeNumber.toString(),
                onValueChange = { socionicTypeNumber = it.toInt() },
                modifier = Modifier
                    .fillMaxWidth()
                    .clearFocusOnKeyboardDismiss(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center)
            )

            Text(
                text = stringResource(id = R.string.personalities),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Start
            )
            var isPersonalitiesTypeExposed by remember {
                mutableStateOf(false)
            }
            var selectedType by rememberSaveable {
                mutableStateOf(Personalities.values().first())
            }
            ExposedDropdownMenuBox(
                expanded = isPersonalitiesTypeExposed,
                onExpandedChange = { isPersonalitiesTypeExposed = it }
            ) {
                TextField(
                    value = stringResource(id = selectedType.stringRes),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = isPersonalitiesTypeExposed
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = isPersonalitiesTypeExposed,
                    onDismissRequest = { isPersonalitiesTypeExposed = false }
                ) {
                    Personalities.values().forEach { currentType ->
                        DropdownMenuItem(onClick = {
                            selectedType = currentType
                            isPersonalitiesTypeExposed = false
                        }) {
                            Text(text = stringResource(id = currentType.stringRes))
                        }
                    }
                }
            }

            Button(
                onClick = {
                    val profileEntity = ProfileEntity(
                        name = name,
                        city = city,
                        age = 0,
                        birthday = DateEntity(0, 0, 0),
                        description = description,
                        zodiacId = 0,
                        fateNumber = 0,
                        socionicTypeNumber = 0,
                        personalitiesNumber = 0,
                        imageLink = ""
                    )
                    onSaveProfile(profileEntity)
                },
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                enabled = name.isNotBlank() && city.isNotBlank() && birthday != null && description.isNotBlank()
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun ProfileEditorViewDisplayPreview() {
    ProfileEditorViewDisplay(null, onSaveProfile = { })
}